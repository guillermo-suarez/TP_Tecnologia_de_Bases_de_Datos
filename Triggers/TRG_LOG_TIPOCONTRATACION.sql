-- Log de TIPOCONTRATACION
create or replace trigger trg_log_tipocontratacion
after update or delete or insert
on tipocontratacion for each row
declare
   usuario varchar2(20);
   fecha date;
begin
  select user into usuario from dual;
  select sysdate into fecha from dual;
  if deleting then 
    insert into log_tipocontratacion values(:OLD.idtipcontrat, :OLD.dentipcontrat, 
                                            'DELETE', usuario, fecha);
  end if;
  if updating then
    insert into log_tipocontratacion values(:OLD.idtipcontrat, :OLD.dentipcontrat,
                                            'UPD-PRE', usuario, fecha);
    insert into log_tipocontratacion values(:NEW.idtipcontrat, :NEW.dentipcontrat,
                                            'UPD-POST', usuario, fecha);
  end if;
  if inserting then
    insert into log_tipocontratacion values(:NEW.idtipcontrat, :NEW.dentipcontrat,
                                            'INSERT', usuario, fecha);
  end if;
end;
