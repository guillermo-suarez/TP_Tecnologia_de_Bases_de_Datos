CREATE OR REPLACE NONEDITIONABLE PROCEDURE PRC_CrearConceptosXCertif (pIdObra obra.idobra%type,
                                                       pFoja foja.idfoja%type,
                                                       pIdCerti certipago.nrocertificado%type)
IS
  vImporte conceptosxcertif.importe%type;
  vAcum conceptosxcertif.importeacuant%type;
  vHayCerti number;
  vPorcentaje conceptosxobra.porcentaje%type;
  vConteo number;
BEGIN
  --Sacamos el importe
  SELECT SUM(fd.monto) into vImporte FROM FOJADET fd WHERE fd.idfoja = pFoja;
  --Declaramos el cursor para poder iterar los conceptos
  DECLARE CURSOR conceptosCerti IS
  SELECT co.idconcepto FROM CONCEPTOSXOBRA co WHERE co.idobra = pIdObra;
  --Creamos la fila del cursor
  filaConceptos conceptosCerti%rowtype;
  --Abrimos el cursor
  BEGIN
            open conceptosCerti;
            vHayCerti:= fun_getultimonrocertipago(pIdObra);
            if (vHayCerti=1) then
              vAcum := 0;
              loop
                fetch conceptosCerti into filaConceptos;
                exit when conceptosCerti%notfound;
                     if (filaConceptos.idconcepto=1) then
                       INSERT INTO CONCEPTOSXCERTIF
                       VALUES (pIdObra, pIdCerti, filaConceptos.idconcepto, vImporte, vAcum);
                     else
                       SELECT co.porcentaje into vPorcentaje FROM conceptosxobra co WHERE co.idconcepto = filaConceptos.idconcepto and co.idobra = pIdObra;
                       DBMS_OUTPUT.put_line(vPorcentaje);
                       vPorcentaje := vPorcentaje/100.0;
                       DBMS_OUTPUT.put_line(to_char(vPorcentaje));
                       INSERT INTO CONCEPTOSXCERTIF
                       VALUES (pIdObra, pIdCerti, filaConceptos.idconcepto, (vImporte * vPorcentaje), vAcum);
                     end if;
                end loop;
            else
              loop
                fetch conceptosCerti into filaConceptos;
                exit when conceptosCerti%notfound;
                     select count(*) into vConteo from conceptosxcertif cc
                     WHERE cc.idconcepto = filaConceptos.idconcepto
                     and cc.idobra = pIdObra
                     and cc.nrocertificado = pIdCerti;
                     if vConteo > 0 then
                       SELECT cc.importeacuant into vAcum FROM conceptosxcertif cc
                       WHERE cc.idconcepto = filaConceptos.idconcepto
                       and cc.idobra = pIdObra
                       and cc.nrocertificado = pIdCerti;
                     else
                       vAcum := 0.0;
                     end if;
                     if (filaConceptos.idconcepto = 1) then
                       INSERT INTO CONCEPTOSXCERTIF
                       VALUES (pIdObra, pIdCerti, filaConceptos.idconcepto, vImporte, vAcum);
                     else
                       SELECT co.porcentaje into vPorcentaje FROM conceptosxobra co WHERE co.idconcepto = filaConceptos.idconcepto and co.idobra = pIdObra;
                       DBMS_OUTPUT.put_line(vPorcentaje);
                       vPorcentaje := vPorcentaje/100.0;
                       DBMS_OUTPUT.put_line(vPorcentaje);
                       INSERT INTO CONCEPTOSXCERTIF
                       VALUES (pIdObra, pIdCerti, filaConceptos.idconcepto, vAcum, (vImporte * vPorcentaje));
                     end if;
                end loop;
            end if;
            close conceptosCerti;
  END;
END;
/
