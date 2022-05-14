-- Log de ITEM
create or replace trigger trg_log_item
after update or delete or insert
on item for each row
declare
   usuario varchar2(20);
   fecha date;
begin
  select user into usuario from dual;
  select sysdate into fecha from dual;
  if deleting then 
    insert into log_item values(:OLD.iditem, :OLD.idobra, :OLD.idtipoitem, :OLD.denitem, :OLD.orden,
                                'DELETE', usuario, fecha);
  end if;
  if updating then
    insert into log_item values(:OLD.iditem, :OLD.idobra, :OLD.idtipoitem, :OLD.denitem, :OLD.orden,
                                'UPD-PRE', usuario, fecha);
    insert into log_item values(:NEW.iditem, :NEW.idobra, :NEW.idtipoitem, :NEW.denitem, :NEW.orden,
                                'UPD-POST', usuario, fecha);
  end if;
  if inserting then
    insert into log_item values(:NEW.iditem, :NEW.idobra, :NEW.idtipoitem, :NEW.denitem, :NEW.orden,
                                'INSERT', usuario, fecha);
  end if;
end;
