-- Log de ITEMCOSTO
create or replace trigger trg_log_itemcosto
after update or delete or insert
on itemcosto for each row
declare
   usuario varchar2(20);
   fecha date;
begin
  select user into usuario from dual;
  select sysdate into fecha from dual;
  if deleting then 
    insert into log_itemcosto values(:OLD.iditem, :OLD.idobra, :OLD.idredeterminacion, :OLD.costo,
                                     'DELETE', usuario, fecha);
  end if;
  if updating then
    insert into log_itemcosto values(:OLD.iditem, :OLD.idobra, :OLD.idredeterminacion, :OLD.costo,
                                     'UPD-PRE', usuario, fecha);
    insert into log_itemcosto values(:NEW.iditem, :NEW.idobra, :NEW.idredeterminacion, :NEW.costo,
                                     'UPD-POST', usuario, fecha);
  end if;
  if inserting then
    insert into log_itemcosto values(:NEW.iditem, :NEW.idobra, :NEW.idredeterminacion, :NEW.costo,
                                     'INSERT', usuario, fecha);
  end if;
end;
