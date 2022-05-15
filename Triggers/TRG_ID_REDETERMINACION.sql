-- Trigger para ID REDETERMINACION autoincremental
create or replace trigger trg_id_redeterminacion
before insert
on redeterminacion for each row
begin
  :NEW.idredeterminacion := seq_id_redeterminacion.nextval;
end;
