FROM ubuntu
RUN apt-get -y update && apt-get -y install nginx
COPY ./volumes/nginx/default.conf /etc/nginx/sites-available/default
EXPOSE 80/tcp
CMD ["/usr/sbin/nginx", "-g", "daemon off;"]