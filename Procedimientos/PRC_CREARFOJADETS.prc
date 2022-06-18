CREATE OR REPLACE NONEDITIONABLE PROCEDURE PRC_CREARFOJADETS(pIdObra obra.idobra%type, pIdFojaAnterior foja.idfoja%type, pIdFojaActual foja.idfoja%type)
IS
vAvance fojadet.avaacuanterior%type;
BEGIN
  --Declaramos un cursos para poder iterar los detalles. Itera iditem
   DECLARE CURSOR detallesFoja is
   SELECT iditem FROM ITEM where item.idobra=pIdObra;
   --Creamos la fila del cursor
   filaDetalles detallesFoja%rowtype;
              --abrimos el cursor
              BEGIN
              open detallesFoja;
              loop
                fetch detallesFoja into filaDetalles; --Asignamos valores en la fila
                exit when detallesFoja%notfound; --Condicion de salida
                  IF pIdFojaAnterior = -1 THEN
                    vAvance:=0;
                  ELSE                    
                    --Asignmos el avance acumulado en vAvance.
                    SELECT avaacuanterior + avaactual into vAvance FROM FOJADET
                    WHERE idfoja = pIdFojaAnterior AND iditem =filaDetalles.iditem;
                  END IF;
                  --Insertamos el detalle con los siguientes valores:
                  --IdFoja, idItem, idObra, avanceAcumulado, NULL avance actual, NULL monto
                  INSERT INTO FOJADET
                  VALUES (pIdFojaActual, filaDetalles.iditem, pIdObra, vAvance, NULL, NULL);
              end loop;
              close detallesFoja; --cerramos el cursor
              END;
END;
/
