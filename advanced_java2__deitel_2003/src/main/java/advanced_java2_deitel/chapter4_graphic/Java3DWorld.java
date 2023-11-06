package advanced_java2_deitel.chapter4_graphic;

import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.behaviors.mouse.MouseTranslate;
import com.sun.j3d.utils.behaviors.mouse.MouseZoom;
import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.universe.ViewingPlatform;

import javax.media.j3d.AmbientLight;
import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingBox;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.Light;
import javax.media.j3d.Material;
import javax.media.j3d.Texture;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import java.awt.*;

public class Java3DWorld extends Canvas3D {
    private Appearance appearance;
    private Light ambientLight;
    private Box shape;
    private Color3f lightColor;
    private Light directionalLight;
    private Material material;
    private SimpleUniverse simpleUniverse;
    private TextureLoader textureLoader;
    private TransformGroup transformGroup;

    private String imageName;

    public Java3DWorld(String imageFileName) {
        super(SimpleUniverse.getPreferredConfiguration());

        imageName = imageFileName;
        simpleUniverse = new SimpleUniverse(this);

        ViewingPlatform viewPlatform = simpleUniverse.getViewingPlatform();
        viewPlatform.setNominalViewingTransform();

        BranchGroup branchGroup = createScene();
        simpleUniverse.addBranchGraph(branchGroup);
    }

    public BranchGroup createScene() {
        BranchGroup scene = new BranchGroup();

        transformGroup = new TransformGroup();
        transformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        transformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);

        scene.addChild(transformGroup);

        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0f, 0.0f, 0.0f), 100.0);

        appearance = new Appearance();
        material = new Material();
        appearance.setMaterial(material);

        String rgb = "RGB";
        textureLoader = new TextureLoader(Java3DWorld.class.getResource(imageName), rgb, this);
        textureLoader.getTexture().setCapability(Texture.ALLOW_ENABLE_WRITE);
        textureLoader.getTexture().setEnable(false);

        appearance.setTexture(textureLoader.getTexture());
        Box shape = new Box(0.3f, 0.3f, 0.3f,
                Box.GENERATE_NORMALS | Box.GENERATE_TEXTURE_COORDS, appearance);
        transformGroup.addChild(shape);

        ambientLight = new AmbientLight();
        ambientLight.setInfluencingBounds(bounds);

        directionalLight = new DirectionalLight();

        lightColor = new Color3f();
        directionalLight.setColor(lightColor);
        directionalLight.setCapability(DirectionalLight.ALLOW_DIRECTION_WRITE);
        directionalLight.setCapability(DirectionalLight.ALLOW_DIRECTION_READ);
        directionalLight.setCapability(DirectionalLight.ALLOW_COLOR_WRITE);
        directionalLight.setCapability(DirectionalLight.ALLOW_COLOR_READ);
        directionalLight.setInfluencingBounds(bounds);

        scene.addChild(ambientLight);
        scene.addChild(directionalLight);

        MouseRotate rotateBehavior = new MouseRotate();
        rotateBehavior.setTransformGroup(transformGroup);
        rotateBehavior.setSchedulingBounds(bounds);

        MouseTranslate translateBehavior = new MouseTranslate();
        translateBehavior.setTransformGroup(transformGroup);
        translateBehavior.setSchedulingBounds(new BoundingBox(
                new Point3d(-1.0f, -1.0f, -1.0f),
                new Point3d(1.0f, 1.0f, 1.0f)));

        MouseZoom scaleBehavior = new MouseZoom();
        scaleBehavior.setTransformGroup(transformGroup);
        scaleBehavior.setSchedulingBounds(bounds);

        scene.addChild(scaleBehavior);
        scene.addChild(rotateBehavior);
        scene.addChild(translateBehavior);

        scene.compile();

        return scene;
    }

    public void changeColor(Color color) {
        lightColor.set(color);
        directionalLight.setColor(lightColor);
    }

    public void updateTexture(boolean textureValue) {
        textureLoader.getTexture().setEnable(textureValue);
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageName() {
        return imageName;
    }

    public Dimension getPreferredSize() {
        return new Dimension(500, 500);
    }

    public Dimension getMinimumSize() {
        return getPreferredSize();
    }

}
