-- Secuencia para ID TipoContratacion autoincremental
create sequence seq_id_tipocontratacion
minvalue 0
maxvalue 999999999
start with 4
/* select MAX(idtipcontrat) + 1 from tipocontratacion; */
increment by 1
nocache;
