-- Log de CONCEPTOSXCERFTIF
create or replace trigger trg_log_conceptosxcertif
after update or delete or insert
on conceptosxcertif for each row
declare
   usuario varchar2(20);
   fecha date;
begin
  select user into usuario from dual;
  select sysdate into fecha from dual;
  if deleting then 
    insert into log_conceptosxcertif values(:OLD.idobra, :OLD.nrocertificado, :OLD.idconcepto, :OLD.importeacuant,
                                            :OLD.importe, 'DELETE', usuario, fecha);
  end if;
  if updating then
    insert into log_conceptosxcertif values(:OLD.idobra, :OLD.nrocertificado, :OLD.idconcepto, :OLD.importe,
                                            :OLD.importe, 'UPD-PRE', usuario, fecha);
    insert into log_conceptosxcertif values(:NEW.idobra, :NEW.nrocertificado, :NEW.idconcepto, :NEW.importe,
                                            :NEW.importe, 'UPD-POST', usuario, fecha);
  end if;
  if inserting then
    insert into log_conceptosxcertif values(:NEW.idobra, :NEW.nrocertificado, :NEW.idconcepto, :NEW.importe,
                                            :NEW.importe, 'INSERT', usuario, fecha);
  end if;
end;
