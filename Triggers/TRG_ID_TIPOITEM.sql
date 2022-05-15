-- Trigger para ID TipoItem Autoincremental
create or replace trigger trg_id_tipoitem
before insert
on tipoitem for each row
begin
  :NEW.idtipoitem := seq_id_tipoitem.nextval;
end;
