package com.ibm.us.vis.geo;
import processing.core.PApplet;

public class pt {
	public float x = 0, y = 0;
		pt next; 
		pt prev; 

		public pt() {
		}
		public pt(float px, float py) {
			x = px;
			y = py;
		}
		public pt(pt P) {
			x = P.x;
			y = P.y;
		}
		
		public static pt mouse(PApplet par){
			return new pt(par.mouseX, par.mouseY); 
		}
		pt(pt P, vec V) {
			x = P.x + V.x;
			y = P.y + V.y;
		}
		pt(pt P, float s, vec V) {
			x = P.x + s * V.x;
			y = P.y + s * V.y;
		}
		pt(pt A, float s, pt B) {
			x = A.x + s * (B.x - A.x);
			y = A.y + s * (B.y - A.y);
		}
		pt self(){
			return new pt(x, y); 
		}

		// MODIFY
		public void setTo(float px, float py) {
			x = px;
			y = py;
		}

		public void setTo(pt P) {
			x = P.x;
			y = P.y;
		}
		void moveWithMouse(pt m) {
			this.setTo(m); 
		}
		void scaleBy(float f) {
			x *= f;
			y *= f;
		}
		void scaleBy(float u, float v) {
			x *= u;
			y *= v;
		}
		void translateBy(vec V) {
			x += V.x;
			y += V.y;
		}
		void translateBy(float u, float v) {
			x += u;
			y += v;
		}
		void translateTowards(float s, pt P) {
			x += s * (P.x - x);
			y += s * (P.y - y);
		}
		void translateBy(float s, vec V) {
			x += s * V.x;
			y += s * V.y;
		}
		
		public void addScaledPt(float s, pt P) {
			x += s * P.x;
			y += s * P.y;
		} 
		
		public void add(int dx, int dy){
			x+=dx; 
			y+=dy; 
		}

		static pt cubicBezier(pt A, pt B, pt C, pt D, float t) {
			return (s(s(s(A, t, B), t, s(B, t, C)), t, s(s(B, t, C), t, s(C, t, D))));
		}

		static pt s(pt A, float s, pt B) { //interpolation
			return new pt(A.x + s * (B.x - A.x), A.y + s * (B.y - A.y));
		}
		static pt s(pt A, pt B){
			return new pt((A.x + B.x)/2, (A.y +B.y)/2); 
		}
		pt s(float s, pt B) {
			return new pt(x + s * (B.x - x), y + s * (B.y - y));
		}
		pt s(vec V){
			return new pt(x + V.x, y + V.y);
		}
		pt s(float s, vec V) {
			if (s == 0) return new pt(this); 
			return new pt(x + s * V.x, y + s * V.y);
		}

		public float disTo(pt P) {
			return (new vec(this, P).n());
		}
		/*pt sub(pt p){
			return new pt(x-p.x, y-p.y);
		}
		pt add(pt p){
			return new pt(x+p.x, y+p.y);
		}
		pt multi(float f){
			return new pt(x*f, y*f);
		}*/
		pt circumCenter (pt A, pt B, pt C) {    // computes the center of a circumscirbing circle to triangle (A,B,C)
			  vec AB = new vec(A,B);  
			  float ab2 = AB.n2();
			  vec AC = new vec(A,C); 
			  AC.turnLeft();  
			  float ac2 = AC.n2();
			  float d = 2*AB.dot(AC);
			  AB.turnLeft();
			  AB.scaleBy(-ac2); 
			  AC.scaleBy(ab2);
			  AB.add(AC);
			  AB.scaleBy((float)1./d);
			  pt X =  new pt(A.x, A.y);
			  X.translateBy(AB);
			  return(X);
			  }

		static boolean isIntersect(pt a1, pt a2, pt b1, pt b2 ){
			return ((isLeftTurn(a1, b1, a2)!= isLeftTurn(a1, b2, a2))&& (isLeftTurn(b1, a1, b2)!=isLeftTurn(b1, a2, b2))); 
		}
		static boolean isLeftTurn(pt a, pt b, pt c){
			vec ab = new vec(a, b); 
			vec bc = new vec(b, c); 
			ab.turnLeft(); 
			float d = ab.dot(bc); 
			if (d >= 0) return true; 
			else return false; 
		}
		static float radius (pt A, pt B, pt C) { //a slightly modified version of circum circle radius
			  vec AB = new vec(A, B); 
			  vec AC = new vec(A, C); 
			  AC.unit(); 
	          float v = A.disTo(C)/2; 
	          float d = AB.dot(AC);
	          pt D = new pt(A); 
	          D.translateBy(d, AC); 
	          float h = (new vec(B, D)).n(); 
	          if (h <0.00001) h = (float)0.00001;
	          float r = v*v/2/h;  
	          if (r > 100000) r = 100001; 
	          return r; 
		}
		static float area(pt A, pt B, pt C){
			vec ab = new vec(A, B);
			vec ac = new vec(A, C);
			return Math.abs(ab.cross(ac)/2); 
		}
	    static float area(pt A, pt B, pt C, pt D){
			return (area(A, B, C)+area(A, C, D)); 
		}
	    static float area(pt A, pt B){ //twice area of the vertical quad bound by AB
	    	return (A.y+B.y)*(B.x - A.x); 
	    }
	    
	    pt laplacian(pt prev, pt next){
	    	return s(self(), s(prev, next)); 
	    }
	    pt laplacian(float l, pt prev, pt next){
	    	return s(self(), l, s(prev, next)); 
	    }
		@Override
		public String toString(){
			return Float.toString(this.x)+","+Float.toString(this.y); 
		}
		public void show(PApplet pa) {
			pa.vertex(x, y);
		}
		void print(String name){
			System.out.print(name+this.toString()); 
		}

}
