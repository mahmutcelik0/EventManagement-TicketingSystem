FROM ubuntu:latest
LABEL authors="mahmu"

ENTRYPOINT ["top", "-b"]