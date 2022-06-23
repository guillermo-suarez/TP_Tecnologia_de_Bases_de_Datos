CREATE OR REPLACE NONEDITIONABLE PROCEDURE PRC_CrearConceptosXCertif (pIdObra obra.idobra%type,
                                                       pFoja foja.idfoja%type,
                                                       pIdCerti certipago.nrocertificado%type)
IS
  vImporte conceptosxcertif.importe%type;
  vAcum conceptosxcertif.importeacuant%type;
  vHayCerti number;
  vPorcentaje conceptosxobra.porcentaje%type;
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
            vHayCerti := fun_getultimonrocertipago(pIdObra) - 1;
            if (vHayCerti = 0) then
              vAcum := 0.00;
              loop
                fetch conceptosCerti into filaConceptos;
                exit when conceptosCerti%notfound;
                     if (filaConceptos.idconcepto = 1) then
                       INSERT INTO CONCEPTOSXCERTIF
                       VALUES (pIdObra, pIdCerti, filaConceptos.idconcepto, vAcum, vImporte);
                     else
                       SELECT co.porcentaje into vPorcentaje FROM conceptosxobra co WHERE co.idconcepto = filaConceptos.idconcepto and co.idobra = pIdObra;
                       vPorcentaje := vPorcentaje/100.0;
                       INSERT INTO CONCEPTOSXCERTIF
                       VALUES (pIdObra, pIdCerti, filaConceptos.idconcepto, vAcum, (vImporte * vPorcentaje));
                     end if;
                end loop;
            else
              loop
                fetch conceptosCerti into filaConceptos;
                exit when conceptosCerti%notfound;
                     select (cc.importeacuant + cc.importe) into vAcum from conceptosxcertif cc
                     where cc.idconcepto = filaConceptos.idconcepto
                     and cc.idobra = pIdObra
                     and cc.nrocertificado = (pIdCerti - 1);
                     if (filaConceptos.idconcepto = 1) then
                       INSERT INTO CONCEPTOSXCERTIF
                       VALUES (pIdObra, pIdCerti, filaConceptos.idconcepto, vAcum, vImporte);
                     else
                       SELECT co.porcentaje into vPorcentaje FROM conceptosxobra co WHERE co.idconcepto = filaConceptos.idconcepto and co.idobra = pIdObra;
                       vPorcentaje := vPorcentaje/100.0;
                       INSERT INTO CONCEPTOSXCERTIF
                       VALUES (pIdObra, pIdCerti, filaConceptos.idconcepto, vAcum, (vImporte * vPorcentaje));
                     end if;
                end loop;
            end if;
            close conceptosCerti;
  END;
END;
/
