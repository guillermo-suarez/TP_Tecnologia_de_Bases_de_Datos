-- Log de CERTIPAGO
create or replace trigger trg_log_certipago
after update or delete or insert
on certipago for each row
declare
   usuario varchar2(20);
   fecha date;
begin
  select user into usuario from dual;
  select sysdate into fecha from dual;
  if deleting then 
    insert into log_certipago values(:OLD.idobra, :OLD.nrocertificado, :OLD.fechacert,
                                     :OLD.abierto, :OLD.idempresa, 'DELETE', usuario, fecha);
  end if;
  if updating then
    insert into log_certipago values(:OLD.idobra, :OLD.nrocertificado, :OLD.fechacert,
                                     :OLD.abierto, :OLD.idempresa, 'UPD-PRE', usuario, fecha);
    insert into log_certipago values(:NEW.idobra, :NEW.nrocertificado, :NEW.fechacert,
                                     :NEW.abierto, :NEW.idempresa, 'UPD-POST', usuario, fecha);
  end if;
  if inserting then
    insert into log_certipago values(:NEW.idobra, :NEW.nrocertificado, :NEW.fechacert,
                                     :NEW.abierto, :NEW.idempresa, 'INSERT', usuario, fecha);
  end if;
end;
