-- Secuencia para ID Localidad autoincremental
create sequence seq_id_localidad
minvalue 0
maxvalue 999999999
start with 97
/* El valor inicial se recupera a partir de:
select MAX(idlocalidad) + 1 from localidad; */
increment by 1
nocache;
