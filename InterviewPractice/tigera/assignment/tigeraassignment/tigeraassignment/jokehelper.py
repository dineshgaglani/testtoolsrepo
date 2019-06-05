"""
This Module talks to external APIs and fetches information from them
"""

import json
import urllib2
import logging

logging.basicConfig()
logger = logging.getLogger("logger")

def get_random_name():
    nameresponse = urllib2.urlopen("https://uinames.com/api/?region=united%20states").read()
    randomnamesjson = json.loads(nameresponse)
    return [randomnamesjson["name"],randomnamesjson["surname"]]

def get_random_joke_with_name(firstname, lastname):
    jokeresponse = urllib2.urlopen("http://api.icndb.com/jokes/random?firstName=%s&lastName=%s&limitTo=\[nerdy\]"%(firstname, lastname)).read()
    randomjokejson = json.loads(jokeresponse)
    return randomjokejson['value']['joke']

def get_random_joke():
    try:
        names=get_random_name()
        return get_random_joke_with_name(names[0], names[1])
    except:
        logger.exception("An error occurred!")
        return "Chuck Norris doesn't throw Exceptions, because he himself is one"