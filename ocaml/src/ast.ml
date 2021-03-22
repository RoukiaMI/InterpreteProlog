(* Termes *)

type term =
  | Var of string                    (* variables *)
  | Func of string * term list        (* functor (arg1, arg2, ...) *)

(* Declarations *)

type dec =
  | Assert of term * (term list)   (* assertion  Head :- Body. *)
  | Goal of (term list)            (* requete       ?- Body.      *)


(* printing functions *)
let rec string_of_term = function
  | Var v -> v
  | Func (f, ls) -> f ^ "(" ^ (String.concat ", " (List.map string_of_term ls)) ^ ")"

let string_of_dec = function
  | Assert (t, []) -> (string_of_term t) ^ "."
  | Assert (t, tl) -> (string_of_term t) ^ " :- " ^ (String.concat ", " (List.map string_of_term tl)) ^ "."
  | Goal tl ->  " ?- " ^ (String.concat ", " (List.map string_of_term tl)) ^ "."

let string_of_dec_list l =
  String.concat "\n" (List.map string_of_dec l)
