package com.ibm.us.vis.geo;


public class vec {
	public float x = 0, y = 0;
	public vec() { 
	};
	vec(vec V) {
		x = V.x;
		y = V.y;
	};
	vec(float s, vec V) {
		x = s * V.x;
		y = s * V.y;
	};
	public vec(float px, float py) {
		x = px;
		y = py;
	};
	public vec(pt P, pt Q) {
		x = Q.x - P.x;
		y = Q.y - P.y;
	};
	vec makeUnit() {
		float n = this.n();
		if (n == 0)
			return new vec(0, 0);
		else
			return new vec(x / n, y / n);
	}
	void unit(){
		float n = this.n(); 
		x = x/n;
		y = y/n; 
	}

	// MODIFY
	public void setTo(float px, float py) {
		x = px;
		y = py;
	};

	void setTo(pt P, pt Q) {
		x = Q.x - P.x;
		y = Q.y - P.y;
	};

	void setTo(vec V) {
		x = V.x;
		y = V.y;
	};

	void scaleBy(float f) {
		x *= f;
		y *= f;
	};
 
	void back() {
		x = -x;
		y = -y;
	};


	void div(float f) {
		x /= f;
		y /= f;
	};

	void scaleBy(float u, float v) {
		x *= u;
		y *= v;
	};

	void add(vec V) {
		x += V.x;
		y += V.y;
	};

	void add(float s, vec V) {
		x += s * V.x;
		y += s * V.y;
	};
	
	vec left(){
		return new vec(-y, x); 
	}
	vec right(){
		return new vec(y, -x); 
	}

	void add(float u, float v) {
		x += u;
		y += v;
	};

	void turnLeft() {
		float w = x;
		x = -y;
		y = w;
	};

	float n() {
		return (float) Math.sqrt(x*x + y*y );
	}
	float n2(){
		return (x*x + y*y);
	}
	
	float dot(vec v) {
		return x*v.x + y*v.y;
	}
	float cross(vec V){
	    return Math.abs(x*V.y - y*V.x); 	
	}
	void print(String name){
		System.out.print(name + ": ("+x+", "+y+") ");
	}
	static float angle(vec u, vec v){
		return (u.x*v.y-u.y*v.x)/(u.n()*v.n()); 
	}
}

