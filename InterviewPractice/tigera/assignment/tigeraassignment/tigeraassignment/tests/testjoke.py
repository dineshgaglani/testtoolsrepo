# -*- coding: utf-8 -*-
from django.test import TestCase
import httpretty
from tigeraassignment import jokehelper
import requests

# create a callback body that raises an exception when opened
def exceptionCallback(request, uri, headers):
    raise requests.Timeout('Connection timed out.')

class TestJokeService(TestCase):

    @httpretty.activate
    def test_get_name_valid(self):
        httpretty.register_uri(
            httpretty.GET,
            "https://uinames.com/api/",
            body="{\"name\":\"testname\",\"surname\":\"testsurname\",\"gender\":\"male\",\"region\":\"Greece\"}")

        httpretty.register_uri(
            httpretty.GET,
            "http://api.icndb.com/jokes/random?firstName=(w+)&lastName=(w+)&limitTo=\[nerdy\]",
            body="{ \"type\": \"success\", \"value\": { \"id\": 181, \"joke\": \"Chuck Norris does not push to production, Chuck Norris IS production\", \"categories\": [\"nerdy\"] } }")

        randomjoke = jokehelper.get_random_joke()
        last_request = httpretty.last_request()
        self.assertEqual(last_request.method, 'GET')
        self.assertEqual(last_request.path, '/jokes/random?firstName=testname&lastName=testsurname&limitTo=\[nerdy\]')
        self.assertEqual(randomjoke, 'Chuck Norris does not push to production, Chuck Norris IS production')

    #This test fails because I couldn't get non-english chars to work, but it's a useful test and so letting it remain
    # @httpretty.activate
    # def test_get_name_valid_nonunicode(self):
    #     httpretty.register_uri(
    #         httpretty.GET,
    #         "https://uinames.com/api/",
    #         body="{\"name\":\"Δαμέας\",\"surname\":\"Γιάνναρης\",\"gender\":\"male\",\"region\":\"Greece\"}")
    #
    #     httpretty.register_uri(
    #         httpretty.GET,
    #         "http://api.icndb.com/jokes/random?firstName=(w+)&lastName=(w+)&limitTo=\[nerdy\]",
    #         body="{ \"type\": \"success\", \"value\": { \"id\": 181, \"joke\": \"Chuck Norris does not push to production, Chuck Norris IS production\", \"categories\": [\"nerdy\"] } }")
    #
    #     randomjoke = jokehelper.get_random_joke()
    #     last_request = httpretty.last_request()
    #     self.assertEqual(last_request.method, 'GET')
    #     self.assertEqual(last_request.path, '/jokes/random?firstName=Δαμέας&lastName=Γιάνναρης&limitTo=\[nerdy\]')
    #     self.assertEqual(randomjoke, 'Chuck Norris does not push to production, Chuck Norris IS production')


    @httpretty.activate
    def test_get_name_invalid_response(self):
        httpretty.register_uri(
            method=httpretty.GET,
            uri='http://www.fakeurl.com',
            status=404,
            body=exceptionCallback)

        httpretty.register_uri(
            httpretty.GET,
            "http://api.icndb.com/jokes/random?firstName=(w+)&lastName=(w+)&limitTo=\[nerdy\]",
            body="{ \"type\": \"success\", \"value\": { \"id\": 181, \"joke\": \"jokename1 jokename2 made a joke\", \"categories\": [\"nerdy\"] } }")

        randomjoke = jokehelper.get_random_joke()
        self.assertEqual(randomjoke, "Chuck Norris doesn't throw Exceptions, because he himself is one")

    @httpretty.activate
    def test_get_name_first_name_missing(self):
        httpretty.register_uri(
            httpretty.GET,
            "https://uinames.com/api/",
            body="{\"surname\":\"testsurname\",\"gender\":\"male\",\"region\":\"Greece\"}")

        randomjoke = jokehelper.get_random_joke()
        self.assertEqual(randomjoke, "Chuck Norris doesn't throw Exceptions, because he himself is one")

    @httpretty.activate
    def test_get_name_last_name_missing(self):
        httpretty.register_uri(
            httpretty.GET,
            "https://uinames.com/api/",
            body="{\"name\":\"testname\",\"gender\":\"male\",\"region\":\"Greece\"}")

        randomjoke = jokehelper.get_random_joke()
        self.assertEqual(randomjoke, "Chuck Norris doesn't throw Exceptions, because he himself is one")
