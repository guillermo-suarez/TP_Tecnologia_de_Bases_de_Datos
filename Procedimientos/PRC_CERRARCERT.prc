CREATE OR REPLACE PROCEDURE SOL.PRC_CERRARCERT (pNumObra obra.numobra%type, pError OUT number)
IS
 vIdObra obra.idobra%type;
 vNroCerti certipago.nrocertificado%type;
 vIdFoja foja.idfoja%type;
 vHayCerti number;
BEGIN
    vIdObra := fun_getidobra(pNumObra);
    vNroCerti := fun_getultimonrocertipago(vIdObra); --Recuperamos el ultimo certificado
    SELECT c.idfoja into vIdFoja FROM CERTIOBRA c WHERE c.nrocertificado = vNroCerti AND c.idobra = vIdObra; --Recuperamos la foja
    vHayCerti := fun_estacertificada(vIdFoja); --Vemos si la foja tiene asociada una foja cerrada o no
    IF (vHayCerti = 1) THEN
      UPDATE CERTIPAGO cp
      SET cp.abierto = 0;
      pError:=0; --No hay error, se cerró el certificado
    ELSE IF (vHayCerti=0) THEN
      pError:=1; --No hay un certificado que cerrar
    ELSE
      pError:=2; --El ultimo certificado ya esta cerrado
    END IF;
    END IF;
END;
/
