-- Trigger para ID TipoContratacion autoincremental
create or replace trigger trg_id_tipocontratacion
before insert
on tipocontratacion for each row
begin
  :NEW.idtipcontrat := seq_id_tipocontratacion.nextval;
end;
