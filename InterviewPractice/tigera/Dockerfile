FROM python:2.7.16-jessie
ENV PYTHONUNBUFFERED 1
RUN mkdir assignment
COPY ./requirements.txt /assignment/
WORKDIR assignment/
RUN pip install -r requirements.txt

CMD ["python", "manage.py", "runserver", "0.0.0.0:8000"]