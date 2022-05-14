-- Log de EMPRESA
create or replace trigger trg_log_empresa
after update or delete or insert
on empresa for each row
declare
   usuario varchar2(20);
   fecha date;
begin
  select user into usuario from dual;
  select sysdate into fecha from dual;
  if deleting then 
    insert into log_empresa values(:OLD.idempresa, :OLD.razonsocial, :OLD.cuit,
                                   'DELETE', usuario, fecha);
  end if;
  if updating then
    insert into log_empresa values(:OLD.idempresa, :OLD.razonsocial, :OLD.cuit,
                                   'UPD-PRE', usuario, fecha);
    insert into log_empresa values(:NEW.idempresa, :NEW.razonsocial, :NEW.cuit,
                                   'UPD-POST', usuario, fecha);
  end if;
  if inserting then
    insert into log_empresa values(:NEW.idempresa, :NEW.razonsocial, :NEW.cuit,
                                   'INSERT', usuario, fecha);
  end if;
end;
