The repo has random tools for automation testing

Few other unrelated useful tips:
Steps to set-up eclipse debugging through jumpbox : 
  1. Start the application in debug mode on your desired port (this process varies for different kinds of servers, tomcat is different from jboss and stuff)
  1. We create a tunnel from the local machine to the jump box: sudo ssh -i ~/localkey -Nf -L 8005:localhost:8005 aws_user@aws_ec2_host_ip
  Here we create a tunnel from 8005 on local host to 8005 on the jump box (http://blog.seyfi.net/2016/06/how-to-remotely-debug-java-applications.html)
  2. Then we create a tunnel from the jumpbox to the node that the service is hosted on using the same command except changing the machine name and pem file.
  3. We then start a remote debug on our eclipse on port 8005 (the port configured in the first)
  
 Sources: https://stackoverflow.com/questions/20658984/remote-debugging-eclipse-via-jump-host
 http://blog.seyfi.net/2016/06/how-to-remotely-debug-java-applications.html
