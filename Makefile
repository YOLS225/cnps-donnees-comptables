
up:
	docker compose -f compose.yaml -p cnps up -d

down:
	docker compose -f compose.yaml -p cnps down \
	&& docker compose rm -fsv \
	&& docker volume rm cnps_postgres_data  cnps_minio_storage

reset: down up


