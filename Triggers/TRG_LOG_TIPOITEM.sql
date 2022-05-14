-- Log de TIPOITEM
create or replace trigger trg_log_tipoitem
after update or delete or insert
on tipoitem for each row
declare
   usuario varchar2(20);
   fecha date;
begin
  select user into usuario from dual;
  select sysdate into fecha from dual;
  if deleting then 
    insert into log_tipoitem values(:OLD.idtipoitem, :OLD.dentipo, 'DELETE', usuario, fecha);
  end if;
  if updating then
    insert into log_tipoitem values(:OLD.idtipoitem, :OLD.dentipo, 'UPD-PRE', usuario, fecha);
    insert into log_tipoitem values(:NEW.idtipoitem, :NEW.dentipo, 'UPD-POST', usuario, fecha);
  end if;
  if inserting then
    insert into log_tipoitem values(:NEW.idtipoitem, :NEW.dentipo, 'INSERT', usuario, fecha);
  end if;
end;
