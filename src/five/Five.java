package five;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Five {
    public static final String START_LOCATION = "seed";
    public static final String END_LOCATION = "location";
    private Map<String, String> nextResourceNameMapping = new HashMap<>();
    private List<Resource> resourceList = new ArrayList<>();
    private List<Long> seeds=new ArrayList<>();;
    private List<Seed> seedsPartTwo=new ArrayList<>();
    private int almanacLineIndex = 0;

    public Five(String path) {
        try {
            List<String> allLines = Files.readAllLines(Paths.get(path));
            this.extractSeeds(allLines.get(almanacLineIndex));
            this.extractSeedsForPartTwo(allLines.get(almanacLineIndex));
            this.almanacLineIndex = 2;
            while (almanacLineIndex < allLines.size()) {
                extractResource(allLines);


            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void extractSeeds(String s) {
        String substring = s.substring(7, s.length());
        String[] stringNums = substring.split(" ");
        for (int i = 0; i < stringNums.length; i++) {
            seeds.add(Long.parseLong(stringNums[i]));
        }
    }

    private void extractSeedsForPartTwo(String s) {
        String substring = s.substring(7, s.length());
        String[] stringNums = substring.split(" ");
        for (int i = 0; i < stringNums.length; i+=2) {
            this.seedsPartTwo.add(new Seed(Long.parseLong(stringNums[i]),Long.parseLong(stringNums[i+1])));
        }
    }

    public long getNearestLocationPartTwo() {
        long nearestLocation = -1;
        String currentResourceName;
        for (int i = 0; i < seedsPartTwo.size(); i++) {
            Seed seed = seedsPartTwo.get(i);
            long currentSourceLocation;
            long seedRange = seed.getSeedRange();
            for (int j = 0; j <seedRange ; j++) {
                currentSourceLocation =seed.getSeedStartLocation()+j;
                currentResourceName = START_LOCATION;
                while (!currentResourceName.equals(END_LOCATION)) {
                    String nextResourceName = this.nextResourceNameMapping.get(currentResourceName);
                    Resource nextResource = this.getNextResource(nextResourceName);

                    currentSourceLocation = nextResource.GetNextSourceLocation(currentSourceLocation);
                    currentResourceName = nextResource.getResourceName();
                }


                if (nearestLocation == -1 || currentSourceLocation < nearestLocation) {
                    nearestLocation = currentSourceLocation;
                }
            }

        }
        return nearestLocation;

    }

    public long GetNearestLocation() {
        long nearestLocation = -1;
        String currentResourceName;
        for (int i = 0; i < seeds.size(); i++) {
            long currentSourceLocation = seeds.get(i);
            currentResourceName = START_LOCATION;
            while (!currentResourceName.equals(END_LOCATION)) {
                String nextResourceName = this.nextResourceNameMapping.get(currentResourceName);
                Resource nextResource = this.getNextResource(nextResourceName);

                currentSourceLocation = nextResource.GetNextSourceLocation(currentSourceLocation);
                currentResourceName = nextResource.getResourceName();
            }

            if (nearestLocation == -1 || currentSourceLocation < nearestLocation) {
                nearestLocation = currentSourceLocation;
            }

        }
        return nearestLocation;
    }

    private Resource getNextResource(String currentResourceName) {
        return this.resourceList.stream()
                .filter(resource -> resource.getResourceName().equals(currentResourceName))
                .findFirst()
                .orElse(null);
    }

    private void extractResource(List<String> almanc) {
        String line = almanc.get(almanacLineIndex);
        String resourceName = extractResourceName(line);
        Resource resource = new Resource();
        resource.setResourceName(resourceName);
        almanacLineIndex++;
        resource.setResourceDetails(this.extractResourceDetails(almanc));
        this.resourceList.add(resource);

    }

    private List<ResourceDetail> extractResourceDetails(List<String> almanc) {
        List<ResourceDetail> resourceDetailsList = new ArrayList<>();
        while (almanacLineIndex < almanc.size() && isLineNotEmpty(almanacLineIndex, almanc)) {
            String line = almanc.get(almanacLineIndex);
            String[] nums = line.split(" ");
            ResourceDetail resourceDetail = createResourceDetail(nums);
            resourceDetailsList.add(resourceDetail);
            almanacLineIndex++;
        }
        almanacLineIndex++;
        return resourceDetailsList;
    }

    private ResourceDetail createResourceDetail(String[] nums) {
        ResourceDetail resourceDetail = new ResourceDetail();
        resourceDetail.setResourceSpreadPoint(Long.parseLong(nums[0]));
        resourceDetail.setSourceTakenSpreadPoint(Long.parseLong(nums[1]));
        resourceDetail.setJump(Long.parseLong(nums[2]));
        return resourceDetail;
    }

    private static boolean isLineNotEmpty(Integer lineIndex, List<String> almanc) {
        return !almanc.get(lineIndex).isEmpty();
    }

    private String extractResourceName(String line) {
        String[] categories = line.split("-to-");
        String firstCategory = categories[0];
        String secondCategory = categories[1].split(" ")[0];
        nextResourceNameMapping.computeIfAbsent(firstCategory, s -> secondCategory);
        return secondCategory;
    }



    public void PrintSeeds() {
        for (long seed : this.seeds) {
            System.out.println(seed + " ");

        }
    }
}
