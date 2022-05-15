-- Trigger para ID FOJA autoincremental
create or replace trigger trg_id_foja
before insert
on foja for each row
begin
  :NEW.idfoja := seq_id_foja.nextval;
end;
