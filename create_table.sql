-- Database librarydb

--DROP DATABASE IF EXISTS librarydb;

CREATE DATABASE librarydb;

-- Table: livres

-- DROP TABLE IF EXISTS livres;

CREATE TABLE IF NOT EXISTS livres
(
    id serial,
    titre text COLLATE pg_catalog."default" NOT NULL,
    auteur text COLLATE pg_catalog."default" NOT NULL,
    categorie text COLLATE pg_catalog."default" NOT NULL,
    nombreexemplaires text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT livres_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS livres
    OWNER to fokanendapascal;


-- Table: membres

-- DROP TABLE IF EXISTS membres;

CREATE TABLE IF NOT EXISTS membres
(
    id serial,
    nom text COLLATE pg_catalog."default" NOT NULL,
    prenom text COLLATE pg_catalog."default",
    email text COLLATE pg_catalog."default",
    adhesiondate date NOT NULL,
    CONSTRAINT membres_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS membres
    OWNER to fokanendapascal;


-- Table: emprunts

-- DROP TABLE IF EXISTS emprunts;

CREATE TABLE IF NOT EXISTS emprunts
(
    idemprunt serial,id
    membreid integer,
    livreid integer,
    dateemprunt date NOT NULL,
    dateretourprevue date NOT NULL,
    dateretoureffective date NOT NULL,
    CONSTRAINT emprunts_pkey PRIMARY KEY (idemprunt),
    CONSTRAINT emprunts_livreid_fkey FOREIGN KEY (livreid)
        REFERENCES livres (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT emprunts_membreid_fkey FOREIGN KEY (membreid)
        REFERENCES membres (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS emprunts
    OWNER to fokanendapascal;
    
    
-- Table: public.penalites

-- DROP TABLE IF EXISTS public.penalites;

CREATE TABLE IF NOT EXISTS public.penalites
(
    penaliteid integer NOT NULL DEFAULT nextval('penalites_penaliteid_seq'::regclass),
    empruntid integer NOT NULL,
    montant numeric(10,2) NOT NULL,
    CONSTRAINT penalites_pkey PRIMARY KEY (penaliteid),
    CONSTRAINT penalites_empruntid_fkey FOREIGN KEY (empruntid)
        REFERENCES public.emprunts (idemprunt) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.penalites
    OWNER to fokanendapascal;    
    
