package com.informatica.p2pms.http.client.agent;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.BuildImageCmd;
import com.github.dockerjava.api.command.CreateContainerCmd;
import com.github.dockerjava.api.command.ExecCreateCmdResponse;
import com.github.dockerjava.api.model.*;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;

import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.core.command.BuildImageResultCallback;
import com.github.dockerjava.core.command.ExecStartResultCallback;
import com.informatica.p2pms.http.client.assets.HttpUtils;
import com.informatica.p2pms.test.TestUtils;
import com.informatica.p2pms.test.framework.UserContext;
import com.informatica.p2pms.test.framework.PropertyUtils;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.commons.lang.StringUtils;
import org.junit.Assert;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.File;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;

import static io.restassured.RestAssured.given;

/*
    The agent downloader uses the pod URL to download the agent
 */
public class AgentHelper {

    private Properties globalProperties;
    private UserContext userContext;

    private static final String AGENT_DOCKER_FILE_PROP_NAME = "agentdockerfile";
    private static final String AGENT_INSTALLER_FILE_PATH = "agentinstallerpath";
    private static final String AGENT_INSTALLER_FILE = "agent64_install.bin";
    public static String AGENT_HOST_NAME = "p2pms-test-agent";
    private static final String IS_TOKEN_AGENT_PROP_NAME = "istokenagent";
    private static final String TEST_DATA_DIR_PATH = "testdatadirpath";

    private String dockerContainerId;
    private String dockerContainerName = "p2pmstest-agent-container";
    private String agentImageName = "p2pmstest-agent-image";

    private String dockerImageId;

    DockerClient dockerClient;

    //Default value should be false
    private boolean RECREATE_AGENT_IMAGE = false;
    private boolean isSkipRegistrationOnRunningContainer = true;
    boolean isTokenAgent;

    /*
        Agent is downloaded from pod url path
     */
    public AgentHelper(Properties globalProperties, UserContext userContext) {
        this.globalProperties = globalProperties;
        this.userContext = userContext;

        String agentOverrideName = globalProperties.getProperty(PropertyUtils.AGENT_NAME_OVERRIDE_PROP_NAME);
        if (agentOverrideName != null) {
            System.out.println("Agent name override provided, will be running tests with agent: " + agentOverrideName);
            AGENT_HOST_NAME = agentOverrideName;
            return;
        }

        isTokenAgent = new Boolean(globalProperties.getProperty(IS_TOKEN_AGENT_PROP_NAME));

        System.out.println("Installing agent using Docker, this step will fail if Docker isn't installed and running");
        //Starting docker with default values
        DockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder()
                .withDockerHost("tcp://localhost:2375").build();
        dockerClient = DockerClientBuilder.getInstance(config).build();
//            dockerClient = DockerClientBuilder.getInstance().build();
        System.out.println("Looks like Docker is running!!");
    }

   public String executeAgentDownloaderScript() {
       //Creates a docker image for agent, starts the agent and registers the user and then returns the created container's id
       String envName = globalProperties.getProperty(PropertyUtils.ENV_PROP_NAME);
       String username = globalProperties.getProperty(PropertyUtils.USERNAME_PROPERTY_NAME);
       /* TODO - Optional - Separate the download, agent start and user login into separate scripts */
        String agentInstallerPath = globalProperties.getProperty(AGENT_INSTALLER_FILE_PATH) + File.separator + AGENT_INSTALLER_FILE;

        //Renaming the agent image by env
       agentImageName = agentImageName + "_" + envName;

        //Renaming the agent container by env and user name
       dockerContainerName = dockerContainerName + "_" + envName + "_" + username;

        File agentInstaller = getAgentInstaller(agentInstallerPath);
        if (!agentInstaller.exists()) {
            String podUrl = userContext.podUrl;
            System.out.println("Agent installer could not be found, so downloading using POD Url: " + podUrl);
            //Download the agent installer
            downloadAgentInstaller(agentInstallerPath, podUrl);
        } else {
            System.out.println("Agent installer found in path : " + agentInstallerPath + ", skipping agent installer" +
                    " download, in case you want a new agent installer delete this file (new agent installer is needed " +
                    "when saas is upgraded or when running on an environment different from the one the current agent " +
                    "installer belongs to");
        }


        List<Image> agentDockerImages = dockerClient.listImagesCmd().withShowAll(true).withImageNameFilter(agentImageName).exec();

        //Boolean hack to get around the atomic boolean constraint, if even one repoTag contains Agent Image Name
        // then agentDockerImageExists is true
        agentDockerImages.stream().forEach(image ->
                    System.out.println("Found an image with tags: " + Arrays.asList(image.getRepoTags()))
        );

        if (agentDockerImages.size() > 0) {
            System.out.println("Docker image with name " + agentImageName + " already exists, going aead with the existing " +
                    "image. If you want to override this behaviour and create a new image, set the boolean flag named " +
                    "'RECREATE_AGENT_IMAGE'  on " + getClass().getName() + " to 'true'");
//                 RECREATE_AGENT_IMAGE = true; // This is un-commented only when new image is needed
            //Finds the image which has a tag with the name of p2pmstest-agent-image
            System.out.println("Getting the image that contains the tag: " + agentImageName);
            dockerImageId = agentDockerImages.get(0).getId();
        }

        if (RECREATE_AGENT_IMAGE || dockerImageId == null) {

            if (RECREATE_AGENT_IMAGE) {
                dockerClient.removeImageCmd(agentImageName).exec();
            }

            //Create a new docker image with the agent installer
            String agentDockerFileName = globalProperties.getProperty(AGENT_DOCKER_FILE_PROP_NAME) + File.separator + "DockerFile";
            Set agentDockerTag = new HashSet();
            agentDockerTag.add(agentImageName);
            BuildImageCmd agentDockerImageBuilder = dockerClient.buildImageCmd(new File(agentDockerFileName)).withTags(agentDockerTag);

            BuildImageResultCallback consoleStreamOutputCallBack = new BuildImageResultCallback() {
                public void onNext(BuildResponseItem item) {
                    super.onNext(item);

                    if (item.isErrorIndicated()) {
                        System.out.println("ERROR: " + item.getErrorDetail().getMessage());
                    } else {
                        System.out.println(StringUtils.chomp(item.getStream(), "\n"));
                    }
                }
            };

            dockerImageId = agentDockerImageBuilder.exec(consoleStreamOutputCallBack).awaitImageId();
        }

        //Create a new container with the image created
        List<Container> allContainers = dockerClient.listContainersCmd().withShowAll(true).withNameFilter(Arrays.asList(dockerContainerName)).exec();

        System.out.println("Checking if container with name " + dockerContainerName + " already exists");
        for (Container c : allContainers) {
            dockerContainerId = c.getId();
            if (c.getState().equals("exited")) {
                System.out.println("Container exists but is not running, hence NOT attempting stopping");
            } else {
                //Means container is 'Running'
                if (isSkipRegistrationOnRunningContainer) {
                    System.out.println("Looks like container is running, this should mean the agent is registered. " +
                            "If that is not the case manually check if this is indeed the correct agent for the env. " +
                            "Skipping Agent Login, in case you want to override this behavior set the " +
                            "'isSkipRegistrationOnRunningContainer' in " + getClass().getName() + " to false");
                    return c.getId();
                } else {
                    System.out.println("Stopping the running container!");
                    dockerClient.stopContainerCmd(dockerContainerId).exec();
                    System.out.println("Successfully stopped container!");
                }
            }
        }

        if (dockerContainerId == null) {
            System.out.println("Creating a new docker container with the name " + dockerContainerName);
            String testDataDirPath = globalProperties.getProperty(TEST_DATA_DIR_PATH);
            CreateContainerCmd createContainerCmd = dockerClient.createContainerCmd(dockerImageId).withName(dockerContainerName).withHostName(AGENT_HOST_NAME);
            if (testDataDirPath != null) {
                String containerVolumePath = "/automationMount";
                System.out.println("Creating volume to mount host path: " + testDataDirPath + ", container path: " + containerVolumePath);
                Volume volume = new Volume(containerVolumePath);
                Bind bind = new Bind(testDataDirPath, volume);
                HostConfig hostConfig = new HostConfig().withBinds(bind);
                createContainerCmd = createContainerCmd.withHostConfig(hostConfig);
            }
            dockerContainerId = createContainerCmd.exec().getId();
        }

        //Start the container
        System.out.println("Starting container named " + dockerContainerName);
        dockerClient.startContainerCmd(dockerContainerName).exec();

        /*
            Container verification script:
                echo "The agent container should have been built by now, if an error occurs on this line, validate that the agent is running on the container using the following lines"
                echo "docker run --name=<somename> p2pmstest-agent-image"
                echo "docker exec <sameNameAsPreviousStep> sh"
                echo "Then once inside the docker container run pwd and verify that the value: /root/infaagent/apps/agentcore is printed"
         */

        ExecCreateCmdResponse agentLoginRequestCommand = null;
        //Execute the consoleAgentManager to register the user - as in https://kb.informatica.com/howto/6/Pages/20/513826.aspx
        if (isTokenAgent) {
            System.out.println("The isTokenAgent flag in env props is enabled, using the token for logging " +
                    "in the user on the agent");

            AgentToken agentTokenClient = new AgentToken(userContext, globalProperties);

            agentLoginRequestCommand = dockerClient.execCreateCmd(dockerContainerId).withAttachStdout(true)
                    .withCmd("./consoleAgentManager.sh", "configureToken",
                            globalProperties.getProperty(PropertyUtils.USERNAME_PROPERTY_NAME),
                            agentTokenClient.getAgentToken()).withAttachStdout(true).exec();
        } else {
            System.out.println("The isTokenAgent flag is disabled, logging in the user using username and password");
            agentLoginRequestCommand = dockerClient.execCreateCmd(dockerContainerId).withAttachStdout(true)
                    .withCmd("./consoleAgentManager.sh", "configure",
                            globalProperties.getProperty(PropertyUtils.USERNAME_PROPERTY_NAME),
                            globalProperties.getProperty(PropertyUtils.PASSWORD_PROPERTY_NAME)).withAttachStdout(true).exec();
        }

        try {
            dockerClient.execStartCmd(agentLoginRequestCommand.getId()).exec(
                    new ExecStartResultCallback(System.out, System.err)).awaitCompletion();

            if (globalProperties.getProperty(PropertyUtils.ENV_PROP_NAME).equals(PropertyUtils.LOCAL_DEV_ENV_VALUE)) {
                String icsdevAgentNetworkName = "icsdev_agent";
                System.out.println("Since env is local, Connecting the docker agent container to " + icsdevAgentNetworkName + " network");
                List<Network> icsdevAgentNetwork = dockerClient.listNetworksCmd().withNameFilter(icsdevAgentNetworkName).exec();
                String networkId = null;
                if (icsdevAgentNetwork.size() != 0) {
                    networkId = icsdevAgentNetwork.get(0).getId();
                } else {
                    System.out.println("Expecting " + icsdevAgentNetworkName + " to be created for the agent to work" +
                            " correctly on local environment, this network is automatically created when the dev " +
                            "setup is run, please execute the dev setup " +
                            "(https://infawiki.informatica.com/pages/viewpage.action?spaceKey=products&title=Starting+the+Docker+Environment)");
                }
                dockerClient.connectToNetworkCmd().withContainerId(dockerContainerId).withNetworkId(networkId).exec();
            }
        } catch (Exception e) {
            Assert.fail("Error while executing command for user login on Docker agent, try manually using the following steps \n" +
                    "Run this command on terminal - 'docker exec " + dockerContainerName + " ./consoleAgentManager.sh " +
                    globalProperties.getProperty(PropertyUtils.USERNAME_PROPERTY_NAME) + " " +
                    globalProperties.getProperty(PropertyUtils.PASSWORD_PROPERTY_NAME) + "'");
        }

        pollDockerAgent(10);

        return dockerContainerId;
   }

   public void pollDockerAgent(Integer maxAttemptsRemaining) {
       //Keep querying agent status until its up. Retry until 3 mins (configured class variable above) and fail
       //if not up until then
       if (maxAttemptsRemaining == 0) {
           System.out.println("Agent login unsuccessful, possible causes: Agent installer being used may not belong to " +
                   "the environment tests are being run on!");
           System.out.println("Printing the server configuration of docker agent..");
           printServerUrlOnAgent(dockerContainerId);
       }
       try {
           ExecCreateCmdResponse agentLoginStatusResult = dockerClient.execCreateCmd(dockerContainerId).withAttachStdout(true).withCmd("./consoleAgentManager.sh",
                   "getstatus").exec();
           ByteArrayOutputStream agentLoginResultHolder = new ByteArrayOutputStream();
           dockerClient.execStartCmd(agentLoginStatusResult.getId()).
                   exec(new ExecStartResultCallback(agentLoginResultHolder, System.err)).awaitCompletion();
           String loginStatus = agentLoginResultHolder.toString("UTF-8");
           if (!loginStatus.contains("READY")) {
               System.out.println("Login status is: " + loginStatus);
               System.out.println("Login status for agent docker is still not READY, re-checking status after 30 seconds");
               Thread.sleep(30 * 1000);
               pollDockerAgent(maxAttemptsRemaining - 1);
           } else {
               System.out.println("Looks like the docker agent is logged in and ready to go!");
           }
       } catch (Exception e) {
           Assert.fail("Exception while trying to getStatus of logged in user on agent, to verify the status of " +
                   "user manually run 'docker exec " + dockerContainerName + " ./consoleAgentManager.sh getstatus' to view" +
                   " error message ");
       }
   }

    private void printServerUrlOnAgent(String agentContainerId) {
        try {
            ExecCreateCmdResponse checkHostnameConfigCommand = dockerClient.execCreateCmd(agentContainerId)
                    .withAttachStdout(true).withCmd("cat", "conf/infagent.ini").exec();
            dockerClient.execStartCmd(checkHostnameConfigCommand.getId()).
                    exec(new ExecStartResultCallback(System.out, System.err)).awaitCompletion();
        } catch (Exception e) {
            System.out.println("Exception when trying to get the ICS Server the agent is configured with");
            e.printStackTrace();
            Assert.fail("Exception when trying to get the ICS Server the agent is configured with");
        }

    }

    private void downloadAgentInstaller(String agentInstallerPath, String podUrl) {
        //This should download the agent to current folder
        String downloadUrl = podUrl + "/download/linux64/installer/agent64_install.bin";
        if (isTokenAgent) {
            downloadUrl = podUrl + "/download/linux64/installer/agent64_install_ng_ext.bin";
        }
        try {
            SSLContext context = SSLContext.getInstance("TLSv1.2");
            //TODO - get the certs for each env and inject them here
            TrustManager[ ] certs = new TrustManager[ ] {
                    new X509TrustManager() {
                        public X509Certificate[] getAcceptedIssuers() {
                            return null;
                        }
                        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {}
                        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {}
                    }
            };
            context.init(null, certs, null);
            HttpsURLConnection.setDefaultSSLSocketFactory(context.getSocketFactory());
            HttpsURLConnection connectionForDownloadUrl = (HttpsURLConnection) new URL(downloadUrl).openConnection();

            System.out.println("Attempting to download agent from URL: " + downloadUrl);

            FileUtils.copyURLToFile(connectionForDownloadUrl.getURL(), new File(agentInstallerPath));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Exception while downloading agent installer from URL: " + downloadUrl);
        }
    }

    private File getAgentInstaller(String agentInstallerPath) {
        System.out.println("Looking for agent installer at path: " + agentInstallerPath);
        File file = new File(agentInstallerPath);
        return file;
    }

    public void usePrebakedAgentImage () {
        String containerId = dockerClient.createContainerCmd("infacloud-iics-docker.jfrog.io/dockeragent:latest")
                            .withName(AGENT_HOST_NAME)
                            .withHostName(AGENT_HOST_NAME)
                            .withCmd(Arrays.asList( new String[] { globalProperties.getProperty(PropertyUtils.USERNAME_PROPERTY_NAME),
                                               globalProperties.getProperty(PropertyUtils.PASSWORD_PROPERTY_NAME),
                                               globalProperties.getProperty(PropertyUtils.IDENTITY_SERVICE_URL_PROP_NAME),
                                               userContext.podUrl
                                             } ))
                           .exec().getId();
        dockerClient.startContainerCmd(containerId).exec();
    }

    public boolean isAgentRegistered() {
        try {
            RequestSpecBuilder getAgentRequestSpecBuilder = HttpUtils.getMicroservicesRequestSpecBuilder(userContext);
            RequestSpecification getAgentRequestSpecification = getAgentRequestSpecBuilder.build();

            Response agentResponse = given().spec(getAgentRequestSpecification)
//                    .log().all()
//                    .expect().statusCode(200)
                    .when()
                    .get("agent/name/" + AGENT_HOST_NAME);
//                  .then().log().all();
//                .jsonPath();

            TestUtils.populateTestContextFromAgentResponse(agentResponse, userContext);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception raised on querying agent: " + AGENT_HOST_NAME + " on pod : " + userContext.podUrl
                    + " using creds from test-run.properties ");
        }

        return false;
    }

    public List<String> getAgents() {

        List<String> agentsInOrg = new ArrayList<>();

        RequestSpecBuilder getAgentRequestSpecBuilder = HttpUtils.getMicroservicesRequestSpecBuilder(userContext);
        RequestSpecification getAgentRequestSpecification = getAgentRequestSpecBuilder.build();

        Response agentResponse = given().spec(getAgentRequestSpecification)
//                    .log().all()
//                    .expect().statusCode(200)
                .when()
                .get("agent/");

        agentResponse.jsonPath().getList("$").stream().forEach(item ->
                    agentsInOrg.add( (String)((HashMap)item).get("agentHost") )
        );

        return agentsInOrg;
    }

    public boolean stopAgentContainer () {
        boolean isDeleted = false;
        if (dockerContainerId != null) {
            System.out.println("Stopping agent container with id: " + dockerContainerId);
            dockerClient.stopContainerCmd(dockerContainerId).exec();
            isDeleted = true;
        } else {
            System.out.println("Docker Agent container does not exist!");
        }
        return isDeleted;
    }

    public boolean deleteAgentContainer () {
        boolean isDeleted = false;
        if (dockerContainerId != null) {
            System.out.println("Removing agent container with id: " + dockerContainerId);
            dockerClient.removeContainerCmd(dockerContainerId).exec();
            isDeleted = true;
        } else {
            System.out.println("Docker Agent container does not exist!");
        }
        return isDeleted;
    }

    public boolean deleteAgentImage () {
        boolean isDeleted = false;
        if (dockerImageId != null) {
            System.out.println("Removing agent image with id: " + dockerImageId);
            dockerClient.removeImageCmd(dockerImageId).exec();
            isDeleted = true;
        } else {
            System.out.println("Docker Agent image does not exist");
        }
        return isDeleted;
    }
}
