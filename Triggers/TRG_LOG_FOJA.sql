-- Log de FOJA
create or replace trigger trg_log_foja
after update or delete or insert
on foja for each row
declare
   usuario varchar2(20);
   fechaop date;
begin
  select user into usuario from dual;
  select sysdate into fechaop from dual;
  if deleting then 
    insert into log_foja values(:OLD.idfoja, :OLD.idobra, :OLD.fecha,
                                'DELETE', usuario, fechaop);
  end if;
  if updating then
    insert into log_foja values(:OLD.idfoja, :OLD.idobra, :OLD.fecha,
                                'UPD-PRE', usuario, fechaop);
    insert into log_foja values(:NEW.idfoja, :NEW.idobra, :NEW.fecha,
                                'UPD-POST', usuario, fechaop);
  end if;
  if inserting then
    insert into log_foja values(:NEW.idfoja, :NEW.idobra, :NEW.fecha,
                                'INSERT', usuario, fechaop);
  end if;
end;
