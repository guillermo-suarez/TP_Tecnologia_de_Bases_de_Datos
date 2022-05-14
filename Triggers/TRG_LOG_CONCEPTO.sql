-- Log de CONCEPTO
create or replace trigger trg_log_concepto
after update or delete or insert
on concepto for each row
declare
   usuario varchar2(20);
   fecha date;
begin
  select user into usuario from dual;
  select sysdate into fecha from dual;
  if deleting then 
    insert into log_concepto values(:OLD.idconcepto, :OLD.denconcepto, 'DELETE', usuario, fecha);
  end if;
  if updating then
    insert into log_concepto values(:OLD.idconcepto, :OLD.denconcepto, 'UPD-PRE', usuario, fecha);
    insert into log_concepto values(:NEW.idconcepto, :NEW.denconcepto, 'UPD-POST', usuario, fecha);
  end if;
  if inserting then
    insert into log_concepto values(:NEW.idconcepto, :NEW.denconcepto, 'INSERT', usuario, fecha);
  end if;
end;
