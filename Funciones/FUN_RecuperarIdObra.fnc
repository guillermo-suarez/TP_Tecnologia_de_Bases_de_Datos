CREATE OR REPLACE FUNCTION FUN_RecuperarIdObra (pNumObra obra.numobra%type)
return obra.idobra%type
is
vIdObra obra.idobra%type;
begin
  SELECT o.idobra into vIdObra FROM OBRA o WHERE o.numobra = pNumObra;
  return vIdObra;
end;
/
