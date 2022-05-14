-- Log de LOCALIDAD
create or replace trigger trg_log_localidad
after update or delete or insert
on localidad for each row
declare
   usuario varchar2(20);
   fecha date;
begin
  select user into usuario from dual;
  select sysdate into fecha from dual;
  if deleting then 
    insert into log_localidad values(:OLD.idlocalidad, :OLD.nomloc, :OLD.cp,
                                     'DELETE', usuario, fecha);
  end if;
  if updating then
    insert into log_localidad values(:OLD.idlocalidad, :OLD.nomloc, :OLD.cp,
                                     'UPD-PRE', usuario, fecha);
    insert into log_localidad values(:NEW.idlocalidad, :NEW.nomloc, :NEW.cp,
                                     'UPD-POST', usuario, fecha);
  end if;
  if inserting then
    insert into log_localidad values(:NEW.idlocalidad, :NEW.nomloc, :NEW.cp,
                                     'INSERT', usuario, fecha);
  end if;
end;
