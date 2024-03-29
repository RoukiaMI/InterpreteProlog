(* ocamlbuild -use-menhir main.ml *)

open Ast
open Format

let string_of_position p =
  Printf.sprintf "%s:%i:%i"
    p.Lexing.pos_fname p.Lexing.pos_lnum
    (p.Lexing.pos_cnum - p.Lexing.pos_bol)
  
let parse lexbuf =
  try
    let ast = Parser.program Lexer.token lexbuf in
    ast
  with
  | Parser.Error ->
      Printf.eprintf "Parse error (invalid syntax) near %s\n" 
        (string_of_position lexbuf.lex_start_p);
      failwith "Parse error"
  | Failure x ->
     if x = "lexing: empty token" then (
       Printf.eprintf "Parse error (invalid token) near %s\n" 
                      (string_of_position lexbuf.lex_start_p);
       failwith "Parse error"
     )
     else raise (Failure x)
    ;;
  
let parse_string (s : string) : dec list =
  parse (Lexing.from_string s)
;;

let parse_file (filename : string) : dec list =
  let f = open_in filename in
  let lexbuf = Lexing.from_channel f in
  lexbuf.lex_curr_p <- { lexbuf.lex_curr_p with pos_fname = filename; };
  let r = parse lexbuf in
  close_in f;
  r
;;

let s = parse_string ("?- p(a,f(a,X)).") in
    Printf.printf "%s\n\n" (Ast.string_of_dec_list s)
;;

let _ =
  List.iter
    (fun f -> Printf.printf "%s\n\n" (Ast.string_of_dec_list (parse_file f)))
    ["exemples/classification.pl";
     "exemples/genealogie.pl";
     "exemples/test_list.pl";
    ]
;;
