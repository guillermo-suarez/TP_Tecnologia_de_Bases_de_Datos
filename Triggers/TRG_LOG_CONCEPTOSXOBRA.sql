-- Log de CONCEPTOSXOBRA
create or replace trigger trg_log_conceptosxobra
after update or delete or insert
on conceptosxobra for each row
declare
   usuario varchar2(20);
   fecha date;
begin
  select user into usuario from dual;
  select sysdate into fecha from dual;
  if deleting then 
    insert into log_conceptosxobra values(:OLD.idobra, :OLD.idconcepto, :OLD.ordenimp, :OLD.porcentaje,
                                          'DELETE', usuario, fecha);
  end if;
  if updating then
    insert into log_conceptosxobra values(:OLD.idobra, :OLD.idconcepto, :OLD.ordenimp, :OLD.porcentaje,
                                          'UPD-PRE', usuario, fecha);
    insert into log_conceptosxobra values(:NEW.idobra, :NEW.idconcepto, :NEW.ordenimp, :NEW.porcentaje,
                                          'UPD-POST', usuario, fecha);
  end if;
  if inserting then
    insert into log_conceptosxobra values(:NEW.idobra, :NEW.idconcepto, :NEW.ordenimp, :NEW.porcentaje,
                                          'INSERT', usuario, fecha);
  end if;
end;
