-- Secuencia para ID TipoItem autoincremental
create sequence seq_id_tipoitem
minvalue 0
maxvalue 999999999
start with 3
/*select MAX(idtipoitem) + 1 from tipoitem;*/
increment by 1
nocache;
