from django.http import HttpResponse
from django.views import View
import jokehelper

class JokeTeller(View):
    def get(self, request):
        randomjoke = jokehelper.get_random_joke()
        return HttpResponse(randomjoke, content_type='text/plain')