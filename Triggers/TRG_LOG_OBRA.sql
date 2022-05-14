-- Log de OBRA
create or replace trigger trg_log_obra
after update or delete or insert
on obra for each row
declare
   usuario varchar2(20);
   fecha date;
begin
  select user into usuario from dual;
  select sysdate into fecha from dual;
  if deleting then 
    insert into log_obra values(:OLD.idobra, :OLD.idlocalidad, :OLD.idtipcontrat, :OLD.idempresa, :OLD.nomobra,
                                :OLD.fecinicio, :OLD.plazo_mes, :OLD.numobra, :OLD.porflete, :OLD.porgastos,
                                :OLD.poruti, :OLD.ivaviv, :OLD.ivainfra, 'DELETE', usuario, fecha);
  end if;
  if updating then
    insert into log_obra values(:OLD.idobra, :OLD.idlocalidad, :OLD.idtipcontrat, :OLD.idempresa, :OLD.nomobra,
                                :OLD.fecinicio, :OLD.plazo_mes, :OLD.numobra, :OLD.porflete, :OLD.porgastos,
                                :OLD.poruti, :OLD.ivaviv, :OLD.ivainfra, 'UPD-PRE', usuario, fecha);
    insert into log_obra values(:NEW.idobra, :NEW.idlocalidad, :NEW.idtipcontrat, :NEW.idempresa, :NEW.nomobra,
                                :NEW.fecinicio, :NEW.plazo_mes, :NEW.numobra, :NEW.porflete, :NEW.porgastos,
                                :NEW.poruti, :NEW.ivaviv, :NEW.ivainfra, 'UPD-POST', usuario, fecha);
  end if;
  if inserting then
    insert into log_obra values(:NEW.idobra, :NEW.idlocalidad, :NEW.idtipcontrat, :NEW.idempresa, :NEW.nomobra,
                                :NEW.fecinicio, :NEW.plazo_mes, :NEW.numobra, :NEW.porflete, :NEW.porgastos,
                                :NEW.poruti, :NEW.ivaviv, :NEW.ivainfra, 'INSERT', usuario, fecha);
  end if;
end;
