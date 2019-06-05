import requests

def testOkResponse():
    r = requests.get('http://localhost:8000/joke')
    assert r.status_code == 200
    assert r.text != ''