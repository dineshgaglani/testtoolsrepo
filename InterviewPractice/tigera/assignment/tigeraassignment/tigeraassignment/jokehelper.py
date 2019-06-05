"""
This Module talks to external APIs and fetches information from them
"""

import json
import urllib2

def get_random_name():
    nameresponse = urllib2.urlopen("https://uinames.com/api/").read()
    randomnamesjson = json.loads(nameresponse)
    return [randomnamesjson["name"],randomnamesjson["surname"]]

def get_random_joke_with_name(firstname, lastname):
    jokeresponse = urllib2.urlopen("http://api.icndb.com/jokes/random?firstName=%s&lastName=%s&limitTo=\[nerdy\]"%(firstname, lastname)).read()
    randomjokejson = json.loads(jokeresponse)
    return randomjokejson['value']['joke']

def get_random_joke():
    names=get_random_name()
    return get_random_joke_with_name(names[0], names[1])