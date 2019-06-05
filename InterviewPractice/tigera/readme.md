creating an image for the docker : \
docker build . -t tigeraassignmentimage

Starting a container for the image: \
docker run -p 8000:8000 tigeraassignmentimage

To run the blackbox test, on the host machine shell, type the below lines \
pip install pytest \
pytest testjokeapi.py

Incase you want to execute the test on the python-django server container itself:\
docker cp testjokeapi.py <container id>:/assignment/ \
docker exec -it <container id> sh \
pip install pytest \
pytest testjokeapi.py


Description:
The project combines 2 apis into 1, the tests are a part of tigera/assignment/tigeraassignment/tigeraassignment/tests folder
There are 4 unit tests: \
Happy path \
First response returns incorrect for first name \
First response returns incorrect for last name \
First response fails \

An additional test that did not pass is: non-ASCII character returned in first request

Another black box test that verifies if the server is up is in the testjokeapi.py file
