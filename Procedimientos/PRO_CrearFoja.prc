--NO ANDA

CREATE OR REPLACE PROCEDURE PRO_CrearFoja (pNumObra obra.numobra%type)
is
vIdObra obra.idobra%type;
vIdFojaAnterior foja.idfoja%type;
vAux foja.idfoja%type; --
vIdFojaCerti foja.idfoja%type;
vIdFojaActual foja.idfoja%type;
vAvance fojadet.avaacuanterior%type;
begin
  SELECT FUN_RecuperarIdObra(pNumObra) into vIdObra from DUAL;
  SELECT MAX(idfoja) into vIdFojaAnterior FROM FOJA f WHERE idobra=vIdObra; 
  SELECT MAX(nrocertificado) into vAux FROM CertiObra WHERE idobra=vIdObra;
  SELECT idfoja into vIdFojaCerti from CERTIOBRA WHERE certiobra.idfoja=vIdFojaAnterior;
  IF vIdFojaAnterior = vIdFojaCerti THEN
   INSERT INTO FOJA
   VALUES(NULL,vIdObra,NULL);
   SELECT MAX(idfoja) into vIdFojaActual FROM FOJA WHERE foja.idobra=vIdObra;
   DECLARE CURSOR detallesFoja is
   SELECT iditem FROM ITEM where item.idobra=vIdObra;
   filaDetalles detallesFoja%rowtype;
            BEGIN
              open detallesFoja;
              loop
                fetch detallesFoja into filaDetalles;
                exit when detallesFoja%notfound;
                  SELECT avaacuanterior + avaactual into vAvance FROM FOJADET
                  WHERE idfoja = vIdFojaAnterior AND iditem =filaDetalles.iditem;
                  INSERT INTO FOJADET
                  VALUES (vIdFojaActual, filaDetalles.iditem, vIdObra, vAvance, NULL, NULL);
              end loop;
              close detallesFoja;
             END;
    END IF;     
end;

