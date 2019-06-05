FROM python:2.7.16-jessie
ENV PYTHONUNBUFFERED 1
mkdir assignment
COPY assignment assignment
RUN pip install -r requirements.txt
WORKDIR assignment/tigeraassignment/tigerassignment
RUN python manage.py test

CMD ["python", "manage.py", "runserver", "0.0.0.0:8000"]