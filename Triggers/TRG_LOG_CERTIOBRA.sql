-- Log de CERTIOBRA
create or replace trigger trg_log_certiobra
after update or delete or insert
on certiobra for each row
declare
   usuario varchar2(20);
   fecha date;
begin
  select user into usuario from dual;
  select sysdate into fecha from dual;
  if deleting then 
    insert into log_certiobra values(:OLD.idobra, :OLD.nrocertificado, :OLD.nrocerobra,
                                     :OLD.idfoja, 'DELETE', usuario, fecha);
  end if;
  if updating then
    insert into log_certiobra values(:OLD.idobra, :OLD.nrocertificado, :OLD.nrocerobra,
                                     :OLD.idfoja, 'UPD-PRE', usuario, fecha);
    insert into log_certiobra values(:NEW.idobra, :NEW.nrocertificado, :NEW.nrocerobra,
                                     :NEW.idfoja, 'UPD-POST', usuario, fecha);
  end if;
  if inserting then
    insert into log_certiobra values(:NEW.idobra, :NEW.nrocertificado, :NEW.nrocerobra,
                                     :NEW.idfoja, 'INSERT', usuario, fecha);
  end if;
end;
