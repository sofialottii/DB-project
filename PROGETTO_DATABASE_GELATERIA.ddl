-- *********************************************
-- * SQL MySQL generation                      
-- *--------------------------------------------
-- * DB-MAIN version: 11.0.2              
-- * Generator date: Sep 14 2021              
-- * Generation date: Sat Jun 22 15:51:14 2024 
-- * LUN file: C:\Users\sofia\Documents\UNIVERSITA'\2° ANNO 23-24\2° SEMESTRE\BASI DI DATI\PROGETTO\PROGETTO_DATABASE_GELATERIA.lun 
-- * Schema: Gelateria/Logico 
-- ********************************************* 


-- Database Section
-- ________________ 

create database Gelateria;
use Gelateria;


-- Tables Section
-- _____________ 

create table CLIENTI (
     CF varchar(10) not null,
     nome varchar(15) not null,
     cognome varchar(20) not null,
     dataNascita date not null,
     e_mail varchar(20),
     dataIscrizione date not null,
     dataDisiscrizione date,
     Reg_CF varchar(10) not null,
     constraint ID_CLIENTE_ID primary key (CF));

create table composizioni (
     CF varchar(10) not null,
     data date not null,
     orario char(1) not null,
     codProdotto varchar(4) not null,
     quantita int not null,
     constraint ID_composizione_ID primary key (codProdotto, CF, data, orario));

create table costituzioni (
     nomeGusto varchar(15) not null,
     codIngrediente varchar(5) not null,
     constraint ID_costituzione_ID primary key (nomeGusto, codIngrediente));

create table DIPENDENTI (
     CF varchar(10) not null,
     nome varchar(15) not null,
     cognome varchar(20) not null,
     dataNascita date not null,
     password char(5) not null,
     IBAN char(27) not null,
     stipendio float(3) not null,
     dataAssunzione date not null,
     dataLicenziamento date,
     constraint ID_DIPENDENTE_ID primary key (CF));

create table DOSI_GUSTO (
     CF varchar(10) not null,
     data date not null,
     orario char(1) not null,
     quantita float(3) not null,
     nomeGusto varchar(15) not null,
     constraint ID_DOSE_GUSTO_ID primary key (CF, data, orario));

create table FORNITORI (
     codFornitore varchar(5) not null,
     nomeAzienda varchar(20) not null,
     recapito varchar(10) not null,
     constraint ID_FORNITORE_ID primary key (codFornitore));

create table GUSTI (
     nomeGusto varchar(15) not null,
     ricetta varchar(50) not null,
     calorieTotali float(5) not null,
     tipoGusto varchar(10) not null,
     disponibilita char,
     periodoDisponibilita varchar(20),
     constraint ID_GUSTO_ID primary key (nomeGusto));

create table INGREDIENTI (
     codIngrediente varchar(5) not null,
     nomeIngrediente varchar(20) not null,
     calorie float(4) not null,
     codFornitore varchar(5) not null,
     constraint ID_INGREDIENTE_ID primary key (codIngrediente));

create table ORDINI (
     CF varchar(10) not null,
     data date not null,
     orario char(1) not null,
     importoTotale float(4) not null,
     Con_CF varchar(10),
     Con_numeroTessera float(3),
     constraint ID_ORDINE_ID primary key (CF, data, orario));

create table partecipazioni (
     CF varchar(10) not null,
     giornoSettimana varchar(10) not null,
     fasciaOraria varchar(15) not null,
     constraint ID_partecipazione_ID primary key (giornoSettimana, fasciaOraria, CF));

create table PRODOTTI (
     codProdotto varchar(4) not null,
     tipoProdotto varchar(10) not null,
     tipoGelato varchar(10),
     numeroGusti int,
     prezzoGelato float(3),
     pesoVaschetta float(3),
     prezzoVaschetta float(3),
     constraint ID_PRODOTTO_ID primary key (codProdotto),
     constraint SID_PRODOTTO_1_ID unique (tipoGelato, numeroGusti),
     constraint SID_PRODOTTO_ID unique (pesoVaschetta));

create table TESSERE (
     CF varchar(10) not null,
     numeroTessera float(3) not null,
     numeroAcquisti int not null,
     constraint ID_TESSERA_ID primary key (CF, numeroTessera));

create table TURNI (
     giornoSettimana varchar(10) not null,
     fasciaOraria varchar(15) not null,
     constraint ID_TURNO_ID primary key (giornoSettimana, fasciaOraria));


-- Constraints Section
-- ___________________ 

alter table CLIENTI add constraint FKregistrazione_FK
     foreign key (Reg_CF)
     references DIPENDENTI (CF);

alter table composizioni add constraint FKcom_PRO
     foreign key (codProdotto)
     references PRODOTTI (codProdotto);

alter table composizioni add constraint FKcom_ORD_FK
     foreign key (CF, data, orario)
     references ORDINI (CF, data, orario);

alter table costituzioni add constraint FKcos_ING_FK
     foreign key (codIngrediente)
     references INGREDIENTI (codIngrediente);

alter table costituzioni add constraint FKcos_GUS
     foreign key (nomeGusto)
     references GUSTI (nomeGusto);

alter table DOSI_GUSTO add constraint FKproduzione_FK
     foreign key (nomeGusto)
     references GUSTI (nomeGusto);

alter table DOSI_GUSTO add constraint FKpreparazione
     foreign key (CF)
     references DIPENDENTI (CF);

-- Not implemented
-- alter table FORNITORI add constraint ID_FORNITORE_CHK
--     check(exists(select * from INGREDIENTI
--                  where INGREDIENTI.codFornitore = codFornitore)); 

-- Not implemented
-- alter table GUSTI add constraint ID_GUSTO_CHK
--     check(exists(select * from costituzioni
--                  where costituzioni.nomeGusto = nomeGusto)); 

alter table INGREDIENTI add constraint FKrifornimento_FK
     foreign key (codFornitore)
     references FORNITORI (codFornitore);

-- Not implemented
-- alter table ORDINI add constraint ID_ORDINE_CHK
--     check(exists(select * from composizioni
--                  where composizioni.CF = CF and composizioni.data = data and composizioni.orario = orario)); 

alter table ORDINI add constraint FKemissione
     foreign key (CF)
     references DIPENDENTI (CF);

alter table ORDINI add constraint FKconvalidazione_FK
     foreign key (Con_CF, Con_numeroTessera)
     references TESSERE (CF, numeroTessera);

alter table ORDINI add constraint FKconvalidazione_CHK
     check((Con_CF is not null and Con_numeroTessera is not null)
           or (Con_CF is null and Con_numeroTessera is null)); 

alter table partecipazioni add constraint FKpar_TUR
     foreign key (giornoSettimana, fasciaOraria)
     references TURNI (giornoSettimana, fasciaOraria);

alter table partecipazioni add constraint FKpar_DIP_FK
     foreign key (CF)
     references DIPENDENTI (CF);

alter table TESSERE add constraint FKassegnazione
     foreign key (CF)
     references CLIENTI (CF);

-- Not implemented
-- alter table TURNI add constraint ID_TURNO_CHK
--     check(exists(select * from partecipazioni
--                  where partecipazioni.giornoSettimana = giornoSettimana and partecipazioni.fasciaOraria = fasciaOraria)); 


-- Index Section
-- _____________ 

create unique index ID_CLIENTE_IND
     on CLIENTI (CF);

create index FKregistrazione_IND
     on CLIENTI (Reg_CF);

create unique index ID_composizione_IND
     on composizioni (codProdotto, CF, data, orario);

create index FKcom_ORD_IND
     on composizioni (CF, data, orario);

create unique index ID_costituzione_IND
     on costituzioni (nomeGusto, codIngrediente);

create index FKcos_ING_IND
     on costituzioni (codIngrediente);

create unique index ID_DIPENDENTE_IND
     on DIPENDENTI (CF);

create unique index ID_DOSE_GUSTO_IND
     on DOSI_GUSTO (CF, data, orario);

create index FKproduzione_IND
     on DOSI_GUSTO (nomeGusto);

create unique index ID_FORNITORE_IND
     on FORNITORI (codFornitore);

create unique index ID_GUSTO_IND
     on GUSTI (nomeGusto);

create unique index ID_INGREDIENTE_IND
     on INGREDIENTI (codIngrediente);

create index FKrifornimento_IND
     on INGREDIENTI (codFornitore);

create unique index ID_ORDINE_IND
     on ORDINI (CF, data, orario);

create index FKconvalidazione_IND
     on ORDINI (Con_CF, Con_numeroTessera);

create unique index ID_partecipazione_IND
     on partecipazioni (giornoSettimana, fasciaOraria, CF);

create index FKpar_DIP_IND
     on partecipazioni (CF);

create unique index ID_PRODOTTO_IND
     on PRODOTTI (codProdotto);

create unique index SID_PRODOTTO_1_IND
     on PRODOTTI (tipoGelato, numeroGusti);

create unique index SID_PRODOTTO_IND
     on PRODOTTI (pesoVaschetta);

create unique index ID_TESSERA_IND
     on TESSERE (CF, numeroTessera);

create unique index ID_TURNO_IND
     on TURNI (giornoSettimana, fasciaOraria);

