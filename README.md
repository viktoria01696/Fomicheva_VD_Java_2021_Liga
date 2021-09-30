# Fomicheva_VD_Java_2021_Liga

## run Docker with PostgreSQL

step 1:
```sh
docker pull postgres
```
step 2:
```sh
docker run --rm --name pgdocker -e POSTGRES_PASSWORD=postgres -e POSTGRES_USER=postgres -e POSTGRES_DB=project_db -d -p 5432:5432 -v $HOME/docker/volumes/postgres:/var/lib/postgresql/data postgres
```