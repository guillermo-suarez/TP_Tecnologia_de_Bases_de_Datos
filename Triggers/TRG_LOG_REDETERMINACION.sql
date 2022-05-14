-- Log de REDETERMINACION
create or replace trigger trg_log_redeterminacion
after update or delete or insert
on redeterminacion for each row
declare
   usuario varchar2(20);
   fecha date;
begin
  select user into usuario from dual;
  select sysdate into fecha from dual;
  if deleting then 
    insert into log_redeterminacion values(:OLD.idredeterminacion, :OLD.fecharedet, :OLD.fechadesde, :OLD.fechahasta,
                                           :OLD.nroresolucion, 'DELETE', usuario, fecha);
  end if;
  if updating then
    insert into log_redeterminacion values(:OLD.idredeterminacion, :OLD.fecharedet, :OLD.fechadesde, :OLD.fechahasta,
                                           :OLD.nroresolucion, 'UPD-PRE', usuario, fecha);
    insert into log_redeterminacion values(:NEW.idredeterminacion, :NEW.fecharedet, :NEW.fechadesde, :NEW.fechahasta,
                                           :NEW.nroresolucion, 'UPD-POST', usuario, fecha);
  end if;
  if inserting then
    insert into log_redeterminacion values(:NEW.idredeterminacion, :NEW.fecharedet, :NEW.fechadesde, :NEW.fechahasta,
                                           :NEW.nroresolucion, 'INSERT', usuario, fecha);
  end if;
end;
