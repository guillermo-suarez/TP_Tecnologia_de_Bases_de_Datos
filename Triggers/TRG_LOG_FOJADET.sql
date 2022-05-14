-- Log de FOJADET
create or replace trigger trg_log_fojadet
after update or delete or insert
on fojadet for each row
declare
   usuario varchar2(20);
   fecha date;
begin
  select user into usuario from dual;
  select sysdate into fecha from dual;
  if deleting then 
    insert into log_fojadet values(:OLD.idfoja, :OLD.iditem, :OLD.idobra, :OLD.avaacuanterior, :OLD.avaactual,
                                   :OLD.monto, 'DELETE', usuario, fecha);
  end if;
  if updating then
    insert into log_fojadet values(:OLD.idfoja, :OLD.iditem, :OLD.idobra, :OLD.avaacuanterior, :OLD.avaactual,
                                   :OLD.monto, 'UPD-PRE', usuario, fecha);
    insert into log_fojadet values(:NEW.idfoja, :NEW.iditem, :NEW.idobra, :NEW.avaacuanterior, :NEW.avaactual,
                                   :NEW.monto, 'UPD-POST', usuario, fecha);
  end if;
  if inserting then
    insert into log_fojadet values(:NEW.idfoja, :NEW.iditem, :NEW.idobra, :NEW.avaacuanterior, :NEW.avaactual,
                                   :NEW.monto, 'INSERT', usuario, fecha);
  end if;
end;
