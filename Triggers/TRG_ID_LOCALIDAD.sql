-- Trigger para ID Localidad autoincremental
create or replace trigger trg_id_localidad
before insert
on localidad for each row
begin
  :NEW.idlocalidad := seq_id_localidad.nextval;
end;
