FROM python:2.7.16-jessie
ENV PYTHONUNBUFFERED 1
RUN mkdir assignment
COPY assignment/* /assignment/
COPY ./requirements.txt /assignment/
RUN pip install -r /assignment/requirements.txt
WORKDIR /assignment
RUN python manage.py test

CMD ["python", "manage.py", "runserver", "0.0.0.0:8000"]