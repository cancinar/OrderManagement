FROM postgres:latest
COPY ./create-multiple-db-postgres/init-db.sql /docker-entrypoint-initdb.d/
RUN chown -R /docker-entrypoint-initdb.d
RUN chmod 755 /docker-entrypoint-initdb.d