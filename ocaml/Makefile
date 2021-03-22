
# variables
TARGET = projet
OCAMLC = ocamlc
OCAMLCFLAGS = -g -I src
OCAMLLEX = ocamllex
OCAMLLEXFLAGS =
OCAMLDEP = ocamldep
MENHIR = menhir
MENHIRFLAGS = --explain
LIBS = 

# sources générées automatiquement (par menhir, camllex)
AUTOGEN = src/lexer.ml src/parser.ml src/parser.mli

# sources à compiler
MLFILES = src/ast.ml src/parser.ml src/lexer.ml src/main.ml
MLIFILES = src/parser.mli

# règles de compilation

CMOFILES = $(MLFILES:%.ml=%.cmo)

$(TARGET): $(CMOFILES)
	$(OCAMLC) -o $@ $(OCAMLCFLAGS) $(LIBS) $+

%.cmo: %.ml %.cmi
	$(OCAMLC) $(OCAMLCFLAGS) $(LIBS) -c $*.ml

%.cmi: %.mli
	$(OCAMLC) $(OCAMLCFLAGS) $(LIBS) -c $*.mli

%.cmo: %.ml
	$(OCAMLC) $(OCAMLCFLAGS) $(LIBS) -c $*.ml

%.ml: %.mll
	$(OCAMLLEX) $(OCAMLLEXFLAGS) $*.mll

%.ml %.mli: %.mly
	$(MENHIR) $(MENHIRFLAGS) $*.mly


# nettoyage
clean:
	rm -f depend $(AUTOGEN) $(TARGET)
	rm -f `find . -name "*.o"`
	rm -f `find . -name "*.a"`
	rm -f `find . -name "*.cm*"`
	rm -f `find . -name "*~"`
	rm -f `find . -name "\#*"`
	rm -f `find . -name "*.conflicts"`

.phony:	clean


# dépendances
depend: $(MLFILES) $(MLIFILES)
	$(OCAMLDEP) $+ > depend

include depend
