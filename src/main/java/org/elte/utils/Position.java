package org.elte.utils;

import java.io.Serializable;

/**
 * A Position osztály egy egyszerű rekord osztály, amely egy pozíciót reprezentál az (x, y)
 * koordináták segítségével.
 */
public record Position(int x, int y) implements Serializable {}
