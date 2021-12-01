package tech.murilo.algorithms.sparsematrixpolynomial;

public class Polynomial {
	
	public static class Term {
		private final int coefficient;
		private final int exp;
		
		public Term(int coefficient, int exp) {
			this.coefficient = coefficient;
			this.exp = exp;
		}
		
		public int getCoefficient() {
			return coefficient;
		}
		
		public int getExp() {
			return exp;
		}
	}
	
	private final int n;
	private final Term[] terms;
	
	public Polynomial(int n) {
		this.n = n;
		this.terms = new Term[this.n];
	}
	
	public Polynomial(int n, Term[] terms) {
		this.n = n;
		this.terms = terms;
	}
	
	public Term[] getTerms() {
		return terms;
	}
	
	public Polynomial sum(Polynomial p) {
		var sumTerms = new Term[this.n + p.n];
		
		int i, j, k;
		i = j = k = 0;
		
		while (i < this.n && j < p.n) {
			if (terms[i].exp > p.terms[j].exp) {
				sumTerms[k++] = terms[i++];
			} else if (terms[i].exp < p.terms[j].exp) {
				sumTerms[k++] = p.terms[j++];
			} else {
				var exp = terms[i].exp;
				sumTerms[k++] = new Term(terms[i++].coefficient + p.terms[j++].coefficient, exp);
			}
		}
		
		for (; i < this.n; i++) {
			sumTerms[k++] = terms[i];
		}
		
		for (; j < p.n; j++) {
			sumTerms[k++] = p.terms[j];
		}
		
		return new Polynomial(k, sumTerms);
	}
	
	public void display() {
		for (int i = 0; i < this.n; i++) {
			System.out.print(String.format("%dx%d" + ((i < this.n - 1) ? "+" : ""), terms[i].coefficient, terms[i].exp));
		}
		
		System.out.println();
	}
	
	public static void main(String[] args) {
		var polynomial01 = new Polynomial(3);
		polynomial01.getTerms()[0] = new Term(1, 3);
		polynomial01.getTerms()[1] = new Term(1, 2);
		polynomial01.getTerms()[2] = new Term(1, 1);
		
		var polynomial02 = new Polynomial(3);
		polynomial02.getTerms()[0] = new Term(5, 2);
		polynomial02.getTerms()[1] = new Term(5, 1);
		polynomial02.getTerms()[2] = new Term(5, 0);
		
		var polynomial03 = polynomial01.sum(polynomial02);
				
		polynomial01.display();
		polynomial02.display();
		polynomial03.display();
	}
}
