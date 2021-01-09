create table public.artista
(codigo integer not null,
nome varchar(400),
PRIMARY KEY (codigo));

create table public.album
(codigo integer not null,
nome varchar(500),
artista_id integer,
primary key (codigo));